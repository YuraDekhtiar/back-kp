package com.dk.backkp.service;

import com.dk.backkp.entity.MyTaskEntity;
import com.dk.backkp.entity.UserEntity;
import com.dk.backkp.exception.BadRequestException;
import com.dk.backkp.repository.TaskRepository;
import com.dk.backkp.security.UserPrincipal;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class MyTaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserAnswerService userAnswerService;
    @Autowired
    private TagService tagService;
    @Autowired
    FileService fileService;

    @PersistenceContext
    private EntityManager entityManager;

    public MyTaskEntity addNewTask(MyTaskEntity myTask, UserPrincipal userPrincipal) {
        UserEntity userEntity = userService.getUserEntityById(userPrincipal.getId());
        myTask.setAuthor(userEntity);
        myTask.getAnswers().stream().forEach(answerEntity -> answerEntity.setTask(myTask));
        myTask.getImages().stream().forEach(imageEntity -> imageEntity.setTask(myTask));
        tagService.add(myTask.getTags());

        return taskRepository.save(myTask);
    }

    public MyTaskEntity getTaskById(Long id)  {
        return taskRepository.findById(id).
                orElseThrow(() -> new BadRequestException(id.toString()));
    }

    public MyTaskEntity getTaskByIdForEdit(Long id, Long userId)  {
        MyTaskEntity myTaskEntity = getTaskById(id);

        if(myTaskEntity.getAuthor().getId() == userId)
            return myTaskEntity;
        else
            throw new BadRequestException(id.toString());
    }

    public boolean deleteByListId(List<Long> listId, Long userId) throws Exception {
        for (Long id:listId) {
            deleteById(id, userId);
        }
        return true;
    }

    public boolean deleteById(Long id, Long userId) throws Exception {
        MyTaskEntity myTask = getTaskById(id);
        if(myTask.getAuthor().getId() == userId) {
            fileService.delete(myTask.getImages());
            taskRepository.delete(myTask);
        }
        else
            throw new BadRequestException(id.toString());
        return true;
    }

    public boolean compareAnswer(Long taskId, String value, Long userId) {
        return userAnswerService.compare(taskId, value, userId);
    }

    public Page<MyTaskEntity> getTasksPage(int page, int limit, String fieldName) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(fieldName).descending());
        return taskRepository.findAll(pageable);
    }

    public MyTaskEntity save(MyTaskEntity myTask) {
        return taskRepository.save(myTask);
    }

    public boolean taskCompleted(Long id, Long userId) {
        return userAnswerService.taskCompleted(id, userId);
    }

    public List<MyTaskEntity> getAllByUserId(Long id) {
        return taskRepository.findAllByAuthor_id(id);
    }


    public List<MyTaskEntity> searchTask(String searchText) {
        FullTextQuery jpaQuery = searchUsersQuery(searchText);

        List<MyTaskEntity> myTaskEntityList = jpaQuery.getResultList();

        return myTaskEntityList;

    }

    private FullTextQuery searchUsersQuery (String searchText) {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(MyTaskEntity.class)
                .get();

        org.apache.lucene.search.Query luceneQuery = queryBuilder
                .keyword()
                .onFields("title", "body", "category", "tags")
                .matching(searchText)
                .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, MyTaskEntity.class);

        return jpaQuery;

    }

}
