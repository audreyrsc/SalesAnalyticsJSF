/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao.jpa;

import com.supmarket.analytics.dao.SaleDao;
import com.supmarket.analytics.entity.AgencyEntity;
import com.supmarket.analytics.entity.ChannelEntity;
import com.supmarket.analytics.entity.ProductEntity;
import com.supmarket.analytics.entity.SaleEntity;
import com.supmarket.analytics.model.SalesModel;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Audrey
 */
@Stateless
public class JpaSaleDao implements SaleDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addSale(SaleEntity sale) {
        try {
            em.persist(sale);
        }catch(PersistenceException e) {
            System.err.println("Add sale: "+e.getMessage());
        }
    }

//    @Override
//    public Collection<SaleEntity> getSalesByAgency(AgencyEntity agency) {
//        List<SaleEntity> sales = new ArrayList<SaleEntity>();
//        
//        try {
//            Query query = em.createQuery("SELECT s FROM SaleEntity s WHERE s.agency= :agency");
//            query.setParameter("agency", agency);
//            sales.addAll(query.getResultList());
//            return sales;
//        } catch(PersistenceException e) {
//            System.err.println(e.getMessage());
//            return sales;
//        }
//    }

    @Override
    public long getCountSales() {
        return (Long) em.createQuery("SELECT COUNT(s) FROM SaleEntity s").getSingleResult();
    }

    @Override
    public BigDecimal getAmountSales() {
        try {
            return (BigDecimal)em.createQuery("SELECT SUM(s.product.prodListPrice) FROM SaleEntity s").getSingleResult();
        }catch(NoResultException e) {
            return BigDecimal.ZERO;
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return BigDecimal.ZERO;
        }
    }

    @Override
    public long countOfSalesByChannel(ChannelEntity channel) {
        try {
            Query query = em.createQuery("SELECT COUNT(s) FROM SaleEntity s WHERE s.channel= :channel");
            query.setParameter("channel", channel);
            return (Long) query.getSingleResult();
        } catch(NoResultException e) {
            return 0L;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return 0L;
        }
    }

    @Override
    public SaleEntity findSaleById(Integer id) {
        return em.find(SaleEntity.class, id);
    }

    @Override
    public long countOfSalesByAgency(AgencyEntity agency) {
        try {
            Query query = em.createQuery("SELECT COUNT(s) FROM SaleEntity s WHERE s.agency= :agency");
            query.setParameter("agency", agency);
            return (Long)query.getSingleResult();
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return 0L;
        }
    }

    @Override
    public long countOfSalesByGender(String gender) {
        try {
            Query query = em.createQuery("SELECT COUNT(s) FROM SaleEntity s WHERE s.customer.custGender= :gender");
            query.setParameter("gender", gender);
            return (Long)query.getSingleResult();
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return 0L;
        }
    }

    @Override
    public long countSalesByMaritalStatus(String maritalStatus) {
        try {
            Query query = em.createQuery("SELECT COUNT(s) FROM SaleEntity s WHERE s.customer.custMaritalStatus= :ms");
            query.setParameter("ms", maritalStatus);
            return (Long)query.getSingleResult();
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return 0L;
        }
    }

    @Override
    public long countSalesByIncomeLevel(String incomeLevel) {
        try {
            Query query = em.createQuery("SELECT COUNT(s) FROM SaleEntity s WHERE s.customer.custIncomeLevel= :incomeL");
            query.setParameter("incomeL", incomeLevel);
            return (Long)query.getSingleResult();
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return 0L;
        }
    }

    @Override
    public BigDecimal getAmoutSalesByFiltre(Map<String, String> params) {
        try {
            StringBuilder builder = new StringBuilder("SELECT SUM(s.product.prodListPrice) FROM SaleEntity s");
            Query query = createDynamicQuery(builder, params);

            return (BigDecimal)query.getSingleResult();
            
        }catch(NoResultException e) {
            return BigDecimal.ZERO;
        }catch(Exception e) {
            System.err.println(e.getMessage());
            return BigDecimal.ZERO;
        }
    }

    @Override
    public long getCountSalesByFilter(Map<String, String> params) {
        try {
            StringBuilder builder = new StringBuilder("SELECT COUNT(s) FROM SaleEntity s");
            Query query = createDynamicQuery(builder, params);

            return (Long)query.getSingleResult();
            
        }catch(NoResultException e) {
            return 0L;
        }catch(Exception e) {
            System.err.println(e.getMessage());
            return 0L;
        }
    }
    
    
    private Query createDynamicQuery(StringBuilder builder, Map<String,String> params) {
        boolean firstClause = true;

        // Construction query //
        for(String key : params.keySet()) {
            builder.append(firstClause? " WHERE " : " AND ");
            if(key.equals("agency")) {
                builder.append("s.agency.name= :");
                builder.append(key);
            } else if(key.equals("gender")) {
                builder.append("s.customer.custGender= :");
                builder.append(key);
            }else if(key.equals("maritalStatus")) {
                builder.append("s.customer.custMaritalStatus= :");
                builder.append(key);
            }else if(key.equals("incomeLevel")) {
                builder.append("s.customer.custIncomeLevel= :");
                builder.append(key);
            }
            firstClause = false;
        }

        // Ajout des paramètres à la query //
        Query query = em.createQuery(builder.toString());
        for(String key : params.keySet()) {
            query.setParameter(key, params.get(key));
        }
        
        return query;
    }

    @Override
    public List<SalesModel> getBestSellingProductsModel() {
        List<SalesModel> sales = new ArrayList<SalesModel>();
        
        try {
            Query query = em.createQuery("SELECT DISTINCT s.product.prodName, COUNT(s) FROM SaleEntity s GROUP BY s.product.prodName ORDER BY COUNT(s) DESC");
            query.setMaxResults(10);
            List<Object[]> result = query.getResultList();

            for(int i=0 ; i<result.size(); i++) {
                sales.add(new SalesModel((String)result.get(i)[0], new Double((Long)result.get(i)[1])));
            }

            return sales;
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return sales;
        }
    }

    @Override
    public List<SalesModel> getBestSellingProductsModelByFilter(Map<String, String> params) {
        List<SalesModel> sales = new ArrayList<SalesModel>();
        
        try {
            StringBuilder builder = new StringBuilder("SELECT DISTINCT s.product.prodName, COUNT(s) FROM SaleEntity s");
            String endQuery = " GROUP BY s.product.prodName ORDER BY COUNT(*) DESC";
            
            boolean firstClause = true;
            
            // Construction query //
            for(String key : params.keySet()) {
                builder.append(firstClause? " WHERE " : " AND ");
                if(key.equals("agency")) {
                    builder.append("s.agency.name= :");
                    builder.append(key);
                } else if(key.equals("gender")) {
                    builder.append("s.customer.custGender= :");
                    builder.append(key);
                }else if(key.equals("maritalStatus")) {
                    builder.append("s.customer.custMaritalStatus= :");
                    builder.append(key);
                }else if(key.equals("incomeLevel")) {
                    builder.append("s.customer.custIncomeLevel= :");
                    builder.append(key);
                }
                firstClause = false;
            }

            builder.append(endQuery);
            
            // Ajout des paramètres à la query //
            Query query = em.createQuery(builder.toString());
            for(String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
            
            query.setMaxResults(10);
            List<Object[]> result = query.getResultList();
        
            for(int i=0 ; i<result.size(); i++) {
                sales.add(new SalesModel((String)result.get(i)[0], new Double((Long)result.get(i)[1])));
            }
            
            return sales;
            
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return sales;
        }
    }

    @Override
    public List<SalesModel> getLessSellingProductsModel() {
        List<SalesModel> sales = new ArrayList<SalesModel>();
        
        try {
            Query query = em.createQuery("SELECT DISTINCT s.product.prodName, COUNT(s) FROM SaleEntity s GROUP BY s.product.prodName ORDER BY COUNT(s) ASC");
            query.setMaxResults(10);
            List<Object[]> result = query.getResultList();

            for(int i=0 ; i<result.size(); i++) {
                sales.add(new SalesModel((String)result.get(i)[0], new Double((Long)result.get(i)[1])));
            }

            return sales;
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return sales;
        }
    }

    @Override
    public List<SalesModel> getLessSellingProductsModelByFilters(Map<String, String> params) {
        List<SalesModel> sales = new ArrayList<SalesModel>();
        
        try {
            StringBuilder builder = new StringBuilder("SELECT DISTINCT s.product.prodName, COUNT(s) FROM SaleEntity s");
            String endQuery = " GROUP BY s.product.prodName ORDER BY COUNT(*) ASC";
            
            boolean firstClause = true;
            
            // Construction query //
            for(String key : params.keySet()) {
                builder.append(firstClause? " WHERE " : " AND ");
                if(key.equals("agency")) {
                    builder.append("s.agency.name= :");
                    builder.append(key);
                } else if(key.equals("gender")) {
                    builder.append("s.customer.custGender= :");
                    builder.append(key);
                }else if(key.equals("maritalStatus")) {
                    builder.append("s.customer.custMaritalStatus= :");
                    builder.append(key);
                }else if(key.equals("incomeLevel")) {
                    builder.append("s.customer.custIncomeLevel= :");
                    builder.append(key);
                }
                firstClause = false;
            }

            builder.append(endQuery);
            
            // Ajout des paramètres à la query //
            Query query = em.createQuery(builder.toString());
            for(String key : params.keySet()) {
                query.setParameter(key, params.get(key));
            }
            
            query.setMaxResults(10);
            List<Object[]> result = query.getResultList();
        
            for(int i=0 ; i<result.size(); i++) {
                sales.add(new SalesModel((String)result.get(i)[0], new Double((Long)result.get(i)[1])));
            }
            
            return sales;
            
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return sales;
        }
    }

    @Override
    public List<SalesModel> getMostLoyalCustomersModel() {
        List<SalesModel> customers = new ArrayList<SalesModel>();
        
        try {
            Query query = em.createQuery("SELECT DISTINCT s.customer.custId, s.customer.custFirstName, s.customer.custLastName, COUNT(s) FROM SaleEntity s GROUP BY s.customer.custId ORDER BY COUNT(s) DESC");
            query.setMaxResults(10);
            List<Object[]> result = query.getResultList();

            for(int i=0 ; i<result.size(); i++) {
                StringBuilder fullName = new StringBuilder();
                fullName.append((String)result.get(i)[1]);
                fullName.append(" ");
                fullName.append((String)result.get(i)[2]);
                
                customers.add(new SalesModel(fullName.toString(), new Double((Long)result.get(i)[3])));
            }

            return customers;
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return customers;
        }
    }

}