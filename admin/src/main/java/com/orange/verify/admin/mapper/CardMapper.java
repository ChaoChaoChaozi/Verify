package com.orange.verify.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.orange.verify.api.bean.Card;
import com.orange.verify.api.vo.CardVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CardMapper extends BaseMapper<Card> {

    @Select("<script>" +
            "SELECT " +
            "c.*,ct.unit as card_type_unit, " +
            "ct.value as card_type_value,a.name as account_name, " +
            "s.name as soft_name " +
            "FROM t_card c " +
            "LEFT JOIN t_card_type ct " +
            "ON c.card_type_id = ct.id " +
            "LEFT JOIN t_soft s " +
            "ON ct.soft_id = s.id " +
            "LEFT JOIN t_account a " +
            "ON c.account_id = a.id " +
            "where c.del_flag = 0 " +
            "<if test=\"cardVo.softId != null and cardVo.softId != ''\"> and ct.soft_id = #{cardVo.softId} </if>" +
            "<if test=\"cardVo.cardTypeUnit != null\"> and ct.unit = #{cardVo.cardTypeUnit} </if>" +
            "<if test=\"cardVo.closure != null\"> and c.closure = #{cardVo.closure} </if>" +
            "<if test=\"cardVo.useStatus != null\"> and c.use_status = #{cardVo.useStatus} </if>" +
            "order by c.create_date desc" +
            "</script>")
    List<CardVo> page(@Param("cardVo") CardVo cardVo, Page page);

}