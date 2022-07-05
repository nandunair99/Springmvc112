package com.narola.spring;

import javax.persistence.*;
import java.util.List;

public class MainClass {

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnit");
            entityManager = entityManagerFactory.createEntityManager();
            EntityTransaction transaction = entityManager.getTransaction();

            transaction.begin();

//            for (int i = 1; i <= 10; i++) {
//                Post post = new Post();
//                post.setPostTitle("XYZ" + i);
//                post.setPostDesc("TEST" + i);
//
//                List<PostComment> postComments = new ArrayList<>();
//                PostComment postComment1 = new PostComment();
//                postComment1.setComment("comment...." + i);
//                postComment1.setPost(post);
//                postComments.add(postComment1);
//
//                PostComment postComment2 = new PostComment();
//                postComment2.setComment("comment...." + i);
//                postComment2.setPost(post);
//                postComments.add(postComment2);
//
//                post.setCommentList(postComments);
//
//                entityManager.persist(post);
//            }


//            entityManager.merge(post);
////            entityManager.remove(post);
////            entityManager.detach(post);

//            Post post1 = entityManager.find(Post.class, 11L);
//            System.out.println(post1.getCommentList());
//            post1.getCommentList().remove(0);
//            System.out.println(post1);
//            entityManager.persist(post1);

//            Univercity univercity = new Univercity();
//            univercity.setSubject("VNSGU1");
//
//            Student student = new Student();
//            student.setFirstName("JAVA8.1");
//            student.setUnivercity(univercity);
//
//            entityManager.persist(student);

//            CustomerPK customerPK = new CustomerPK("9990", "java1@n.com");
//            Customer customer = new Customer();
//            customer.setCustomerPK(customerPK);
//            customer.setRegistrationNumber("101011");
//            entityManager.persist(customer);

//            Customer customer = entityManager.find(Customer.class, new CustomerPK("9990", "java@n.com"));
            Query query = entityManager.createQuery("SELECT c from Customer c WHERE c.customerPK.custMob = :mob AND c" +
                    ".registrationNumber = :refNo");
            List<Customer> customerList = query
                    .setParameter("mob", "9990")
                    .setParameter("refNo", "101011")
                    .getResultList();
            System.out.println(customerList.size());

            transaction.commit();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (entityManagerFactory != null) {
                entityManagerFactory.close();
            }
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

}
