package com.smip.entity;


/**entity基类
 * 用于控制前台传送数据做CRUD时对属性的控制
 * 规定了字段区间查询，传入对应属性名
 * 例：查询bscresident.id>500的，
 * Bscresident person = new Bscresident();
 * person.id = 500;
 * person.setMoreThan(StringUtil.getEntityParamName(person.id));
 * 后台拿到moreThan这个字段之后，提取该属性名和该属性名对应的属性做sql查询。（为空则跳过）
 * Created by kepler@gmail.com on 2017/11/16.
 */
public class QueryEntity {
    private String[] startDate;
    private String[] endDate;
    private String[] moreThan; //大于。set属性名(对应属性只能为数字类型)
    private String[] lessThan; //小于。同上
    private String[] absEquel; //等于。set属性名。该项为空就默认模糊查询(任何类型)
    private boolean fuzzyMatch;//模糊查询状态,该选项监控所有大于小于查询，若填写了morethan,lessthan该属性false,反之true

    public String[] getMoreThan() {
        return moreThan;
    }

    public void setMoreThan(String... params) {
        String[] str = null;
        for (int i=0;i<params.length;i++){
            str[i] = params[i];
        }
        this.moreThan = str;
    }

    public String[] getAbsEquel() {
        return absEquel;
    }

    public void setAbsEquel(String... params) {
        String[] str = null;
        for (int i=0;i<params.length;i++){
            str[i] = params[i];
        }
        this.absEquel = str;
    }

    public String[] getLessThan() {
        return lessThan;
    }

    public void setLessThan(String... params) {
        String[] str = null;
        for (int i=0;i<params.length;i++){
            str[i] = params[i];
        }
        this.lessThan = str;
    }

    public String[] getStartDate() {
        return startDate;
    }

    public void setStartDate(String... dateParams) {
        String[] str = null;
        for (int i=0;i<dateParams.length;i++){
            str[i] = dateParams[i];
        }
        this.startDate = str;
    }

    public String[] getEndDate() {
        return endDate;
    }

    public void setEndDate(String... dateParams) {
        String[] str = null;
        for (int i=0;i<dateParams.length;i++){
            str[i] = dateParams[i];
        }
        this.endDate = str;
    }
}
