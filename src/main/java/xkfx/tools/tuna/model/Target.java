package xkfx.tools.tuna.model;

public class Target {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.id
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.name
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column target.comment
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    private String comment;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.id
     *
     * @return the value of target.id
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.id
     *
     * @param id the value for target.id
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.name
     *
     * @return the value of target.name
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.name
     *
     * @param name the value for target.name
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column target.comment
     *
     * @return the value of target.comment
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    public String getComment() {
        return comment;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column target.comment
     *
     * @param comment the value for target.comment
     *
     * @mbg.generated Sat Mar 09 19:07:41 GMT+08:00 2019
     */
    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    @Override
    public String toString() {
        return "Target{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}