package cn.javamap.book.pojo;

import java.io.Serializable;

/**
 * 对应数据表 ` book_category` 的书籍分类实体类
 */
public class Bookcategory implements Serializable {
    private static final long serialVersionUID = 6905654542174676691L;
    private Integer categoryId;

    private String categoryName;

    public Bookcategory(Integer categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Bookcategory() {
        super();
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", categoryId=").append(categoryId);
        sb.append(", categoryName=").append(categoryName);
        sb.append("]");
        return sb.toString();
    }
}