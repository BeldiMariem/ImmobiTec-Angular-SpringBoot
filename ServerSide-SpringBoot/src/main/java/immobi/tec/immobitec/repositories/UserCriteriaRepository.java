package immobi.tec.immobitec.repositories;

import immobi.tec.immobitec.entities.AppUser;
import immobi.tec.immobitec.entities.UserPage;
import immobi.tec.immobitec.entities.UserSearchCriteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Repository
public class UserCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public UserCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<AppUser> findAllWithFilters(UserPage userPage,
                                            UserSearchCriteria userSearchCriteria){
        CriteriaQuery<AppUser> criteriaQuery = criteriaBuilder.createQuery(AppUser.class);
        Root<AppUser> userRoot = criteriaQuery.from(AppUser.class);
        Predicate predicate = getPredicate(userSearchCriteria, userRoot);
        criteriaQuery.where(predicate);
        setOrder(userPage, criteriaQuery, userRoot);

        TypedQuery<AppUser> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(userPage.getPageNumber() * userPage.getPageSize());
        typedQuery.setMaxResults(userPage.getPageSize());

        Pageable pageable = getPageable(userPage);

        long usersCount = getUsersCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, usersCount);
    }

    private Predicate getPredicate(UserSearchCriteria userSearchCriteria,
                                   Root<AppUser> userRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(userSearchCriteria.getName())){
            predicates.add(
                    criteriaBuilder.like(userRoot.get("name"),
                            "%" + userSearchCriteria.getName() + "%")
            );
        }
        if(Objects.nonNull(userSearchCriteria.getLastname())){
            predicates.add(
                    criteriaBuilder.like(userRoot.get("lastname"),
                            "%" + userSearchCriteria.getLastname() + "%")
            );
        }
        if(Objects.nonNull(userSearchCriteria.getEmail())){
            predicates.add(
                   criteriaBuilder.like(userRoot.get("email"),
                            "%" + userSearchCriteria.getEmail() + "%")
            );
        }
       
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(UserPage userPage,
                          CriteriaQuery<AppUser> criteriaQuery,
                          Root<AppUser> userRoot) {
        if(userPage.getSortDirection().equals(Sort.Direction.ASC)){
            criteriaQuery.orderBy(criteriaBuilder.asc(userRoot.get(userPage.getSortBy())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.desc(userRoot.get(userPage.getSortBy())));
        }
    }

    private Pageable getPageable(UserPage userPage) {
        Sort sort = Sort.by(userPage.getSortDirection(), userPage.getSortBy());
        return PageRequest.of(userPage.getPageNumber(),userPage.getPageSize(), sort);
    }

    private long getUsersCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<AppUser> countRoot = countQuery.from(AppUser.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
