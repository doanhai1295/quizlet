package com.quizlet.core.app.utils;

import javax.transaction.SystemException;
import org.springframework.util.CollectionUtils;

public class QueryBuilder  extends AbstractQueryBuilder{

    @Override
    public String string() throws Exception {
        if (this.from == null) {
            throw new SystemException("Error query");
        } else {
            StringBuilder query = new StringBuilder();
            if (!CollectionUtils.isEmpty(this.fields)) {
                query.append("SELECT ").append(String.join(", ", this.fields));
            }
            query.append(this.stringQueryAfterSelect());
            System.out.println("QUERY = " + query.toString());
            return query.toString();
        }
    }

}
