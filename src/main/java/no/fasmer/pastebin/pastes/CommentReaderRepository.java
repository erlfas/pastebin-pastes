package no.fasmer.pastebin.pastes;

import org.springframework.data.repository.Repository;
import reactor.core.publisher.Flux;

public interface CommentReaderRepository extends Repository<Comment, String> {

    Flux<Comment> findByPasteId(String pasteId);

}
