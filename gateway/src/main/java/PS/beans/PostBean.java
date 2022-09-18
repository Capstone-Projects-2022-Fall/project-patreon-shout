package PS.beans;

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
    protected int post_id;


}
