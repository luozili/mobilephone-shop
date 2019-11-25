package net.hycollege.mapper;

import java.util.List;
import java.util.Map;
import net.hycollege.mybatis.domain.ProductDesc;
import net.hycollege.mybatis.domain.ProductDescExample.Criteria;
import net.hycollege.mybatis.domain.ProductDescExample.Criterion;
import net.hycollege.mybatis.domain.ProductDescExample;
import org.apache.ibatis.jdbc.SQL;

public class ProductDescSqlProvider {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String countByExample(ProductDescExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("t_product_desc");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String deleteByExample(ProductDescExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("t_product_desc");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String insertSelective(ProductDesc record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_product_desc");
        
        if (record.getPdid() != null) {
            sql.VALUES("pdid", "#{pdid,jdbcType=VARCHAR}");
        }
        
        if (record.getPdesc() != null) {
            sql.VALUES("pdesc", "#{pdesc,jdbcType=LONGVARCHAR}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String selectByExampleWithBLOBs(ProductDescExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("pdid");
        } else {
            sql.SELECT("pdid");
        }
        sql.SELECT("pdesc");
        sql.FROM("t_product_desc");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String selectByExample(ProductDescExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("pdid");
        } else {
            sql.SELECT("pdid");
        }
        sql.FROM("t_product_desc");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String updateByExampleSelective(Map<String, Object> parameter) {
        ProductDesc record = (ProductDesc) parameter.get("record");
        ProductDescExample example = (ProductDescExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("t_product_desc");
        
        if (record.getPdid() != null) {
            sql.SET("pdid = #{record.pdid,jdbcType=VARCHAR}");
        }
        
        if (record.getPdesc() != null) {
            sql.SET("pdesc = #{record.pdesc,jdbcType=LONGVARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_product_desc");
        
        sql.SET("pdid = #{record.pdid,jdbcType=VARCHAR}");
        sql.SET("pdesc = #{record.pdesc,jdbcType=LONGVARCHAR}");
        
        ProductDescExample example = (ProductDescExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("t_product_desc");
        
        sql.SET("pdid = #{record.pdid,jdbcType=VARCHAR}");
        
        ProductDescExample example = (ProductDescExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    public String updateByPrimaryKeySelective(ProductDesc record) {
        SQL sql = new SQL();
        sql.UPDATE("t_product_desc");
        
        if (record.getPdesc() != null) {
            sql.SET("pdesc = #{pdesc,jdbcType=LONGVARCHAR}");
        }
        
        sql.WHERE("pdid = #{pdid,jdbcType=VARCHAR}");
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_product_desc
     *
     * @mbg.generated Fri Nov 08 16:14:17 CST 2019
     */
    protected void applyWhere(SQL sql, ProductDescExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}