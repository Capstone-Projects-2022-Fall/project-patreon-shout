package PS.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="posts")
public class PostBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="post_id")
    protected int post_id;

    @Column(name="publishdate")
    protected String publishdate;

    @Column(name="title")
    protected String title;

    @Column(name="url")
    protected String url;

    @Column(name="content")
    protected String content;

    @Column(name="isprivate")
    protected boolean isprivate;

    @Column(name="creator")
    protected String creator;
}
