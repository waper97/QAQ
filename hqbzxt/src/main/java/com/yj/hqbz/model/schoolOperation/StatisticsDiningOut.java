package com.yj.hqbz.model.schoolOperation;

/**
 * @Title
 * @Description:    今日计划出餐统计
 * @Author:         wangpeng
 * @CreateDate:     2019/4/15 14:37
 */
public class StatisticsDiningOut {
    //计划出餐总数
    private Integer total;
    //早餐
    private Integer breakfast;
    //午餐
    private Integer lunch;
    //晚餐
    private Integer dinner;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getLunch() {
        return lunch;
    }

    public void setLunch(Integer lunch) {
        this.lunch = lunch;
    }

    public Integer getDinner() {
        return dinner;
    }

    public void setDinner(Integer dinner) {
        this.dinner = dinner;
    }
}
