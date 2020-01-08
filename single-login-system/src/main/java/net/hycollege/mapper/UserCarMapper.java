package net.hycollege.mapper;

import java.util.List;
import net.hycollege.mybatis.domain.UserCar;
import net.hycollege.mybatis.domain.UserCarExample;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface UserCarMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @SelectProvider(type=UserCarSqlProvider.class, method="countByExample")
    long countByExample(UserCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @DeleteProvider(type=UserCarSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @Delete({
        "delete from t_user_car",
        "where cid = #{cid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @Insert({
        "insert into t_user_car (cid, uid, ",
        "pid, pnumber)",
        "values (#{cid,jdbcType=VARCHAR}, #{uid,jdbcType=VARCHAR}, ",
        "#{pid,jdbcType=VARCHAR}, #{pnumber,jdbcType=INTEGER})"
    })
    int insert(UserCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @InsertProvider(type=UserCarSqlProvider.class, method="insertSelective")
    int insertSelective(UserCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @SelectProvider(type=UserCarSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.VARCHAR),
        @Result(column="pnumber", property="pnumber", jdbcType=JdbcType.INTEGER)
    })
    List<UserCar> selectByExample(UserCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @Select({
        "select",
        "cid, uid, pid, pnumber",
        "from t_user_car",
        "where cid = #{cid,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="cid", property="cid", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="uid", property="uid", jdbcType=JdbcType.VARCHAR),
        @Result(column="pid", property="pid", jdbcType=JdbcType.VARCHAR),
        @Result(column="pnumber", property="pnumber", jdbcType=JdbcType.INTEGER)
    })
    UserCar selectByPrimaryKey(String cid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @UpdateProvider(type=UserCarSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserCar record, @Param("example") UserCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @UpdateProvider(type=UserCarSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserCar record, @Param("example") UserCarExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @UpdateProvider(type=UserCarSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserCar record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_car
     *
     * @mbg.generated Wed Dec 25 16:36:25 CST 2019
     */
    @Update({
        "update t_user_car",
        "set uid = #{uid,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=VARCHAR},",
          "pnumber = #{pnumber,jdbcType=INTEGER}",
        "where cid = #{cid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(UserCar record);
}