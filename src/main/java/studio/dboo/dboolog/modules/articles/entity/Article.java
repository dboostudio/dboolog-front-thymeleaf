package studio.dboo.dboolog.modules.articles.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import studio.dboo.dboolog.modules.categories.entity.Category;
import studio.dboo.dboolog.modules.comments.entity.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Article {

    @Id @GeneratedValue
    Long id;
    String filepath;

    @OneToOne
    Category category;

    @OneToMany(mappedBy = "article")
    List<Comment> comments = new ArrayList<Comment>();

    /** Logging Data Manipulation **/
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime modifiedAt;
    private LocalDateTime droppedAt;
}