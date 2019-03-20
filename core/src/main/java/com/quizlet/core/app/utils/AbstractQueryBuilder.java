package com.quizlet.core.app.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.SystemException;
import org.springframework.util.CollectionUtils;

public abstract class AbstractQueryBuilder {

    protected List<String> fields;
    protected Object from;
    protected List<String> havings;
    protected List<String> wheres;
    protected List<String> groupBys;
    protected List<String> orderBys;
    protected Integer firstResult;
    protected Integer maxResult;
    protected Map<String, Object> paramsName;
    protected Map<Integer, Object> paramsPosition;

    /**
     * to string (sql or hql)
     *
     * @return sql or hql
     * @throws Exception
     */
    public abstract String string() throws Exception;

    /**
     * add select fields
     *
     * @param fields the fields
     * @return a reference to this object.
     */
    public AbstractQueryBuilder select(String... fields) {
        if (this.fields == null) {
            this.fields = new ArrayList<>();
        }
        this.fields.addAll(Arrays.asList(fields));
        return this;
    }
    
    /**
     * set table
     * @param from
     * @return
     */
    public AbstractQueryBuilder from(Object from) {
        this.from = from;
        return this;
    }

    /**
     * add where condition
     *
     * @param where the where condition
     * @return a reference to this object.
     */
    public AbstractQueryBuilder where(String where) {
        if (this.wheres == null) {
            this.wheres = new ArrayList<>();
        }
        this.wheres.add(where);
        return this;
    }

    /**
     * add having expression
     *
     * @param having the having expression
     * @return a reference to this object.
     */
    public AbstractQueryBuilder having(String having) {
        if (this.havings == null) {
            this.havings = new ArrayList<>();
        }
        this.havings.add(having);
        return this;
    }

    /**
     * add group field
     *
     * @param groupBys the fields
     * @return a reference to this object.
     */
    public AbstractQueryBuilder groupBy(String... groupBy) {
        if (this.groupBys == null) {
            this.groupBys = new ArrayList<>();
        }
        this.groupBys.addAll(Arrays.asList(groupBy));
        return this;
    }

    /**
     * add order fields
     *
     * @param orderBy the order fields
     * @return
     */
    public AbstractQueryBuilder orderBy(String... orderBy) {
        if (this.orderBys == null) {
            this.orderBys = new ArrayList<>();
        }
        this.orderBys.addAll(Arrays.asList(orderBy));
        return this;
    }

    /**
     * add a named query parameter
     *
     * @param name the parameter name
     * @param value the parameter value
     * @return a reference to this object.
     */
    public AbstractQueryBuilder setLikeParameter(String name, Object value) {
        if (this.paramsName == null) {
            this.paramsName = new HashMap<>();
        }
        this.paramsName.put(name, "%" + value + "%");
        return this;
    }

     /**
     * add a positional query parameter
     *
     * @param position the parameter postion
     * @param value the parameter value
     * @return a reference to this object.
     */
    public AbstractQueryBuilder setLikeParameter(int position, Object value) {
        if (this.paramsPosition == null) {
            this.paramsPosition = new HashMap<>();
        }
        this.paramsPosition.put(position, "%" + value + "%");
        return this;
    }
    /**
     * add a named query parameter
     *
     * @param name the parameter name
     * @param value the parameter value
     * @return a reference to this object.
     */
    public AbstractQueryBuilder setParameter(String name, Object value) {
        if (this.paramsName == null) {
            this.paramsName = new HashMap<>();
        }
        this.paramsName.put(name, value);
        return this;
    }
    
    /**
     * add a positional query parameter
     *
     * @param position the parameter postion
     * @param value the parameter value
     * @return a reference to this object.
     */
    public AbstractQueryBuilder setParameter(int position, Object value) {
        if (this.paramsPosition == null) {
            this.paramsPosition = new HashMap<>();
        }
        this.paramsPosition.put(position, value);
        return this;
    }

    /**
     * add a positional query parameter
     *
     * @param position the parameter postion
     * @param value the parameter value
     * @return a reference to this object.
     */
    public AbstractQueryBuilder setFirstResult(int firstResult) {
        this.firstResult = firstResult;
        return this;
    }

     /**
     * set max result
     *
     * @param maxResult the max result index
     * @return a reference to this object.
     */
    public AbstractQueryBuilder setMaxResult(int maxResult) {
        this.maxResult = maxResult;
        return this;
    }


    protected StringBuilder stringQueryAfterSelect() {
        StringBuilder query = new StringBuilder();
        query.append(" From ").append(this.from);
        if (!CollectionUtils.isEmpty(this.wheres)) {
            query.append(" WHERE ").append(String.join(" AND ", this.wheres));
        }
        if (!CollectionUtils.isEmpty(this.groupBys)) {
            query.append(" GROUP BY ").append(String.join(", ", this.groupBys));
        }
        if (!CollectionUtils.isEmpty(this.havings)) {
            query.append(" HAVING ").append(String.join(" AND  ", this.havings));
        }
        if (!CollectionUtils.isEmpty(this.orderBys)) {
            query.append(" ORDER BY ").append(String.join(", ", this.orderBys));
        }
        return query;
    }

    /**
     * Query builder
     *
     * @param sessionFactory session factory
     * @return {@link org.hibernate.query.Query}
     * @throws SystemException
     */
    public Query build(EntityManager entityManager) throws Exception {
        Query query = entityManager.createQuery(this.string());
        this.setParameters(query);
        return query;
    }

    /**
     * set parameters
     *
     * @param query the query
     */
    private void setParameters(Query query) {
        // set parameters
        if (!CollectionUtils.isEmpty(paramsName)) {
            paramsName.forEach(query::setParameter);
        }
        if (!CollectionUtils.isEmpty(paramsPosition)) {
            paramsPosition.forEach(query::setParameter);
        }
        // set max result
        if (this.maxResult != null) {
            query.setMaxResults(this.maxResult);
        }
        // set first result
        if (this.firstResult != null) {
            query.setFirstResult(this.firstResult);
        }
    }

}
