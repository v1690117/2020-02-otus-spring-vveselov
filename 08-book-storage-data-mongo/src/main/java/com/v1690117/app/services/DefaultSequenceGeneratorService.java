package com.v1690117.app.services;

import com.v1690117.app.model.DatabaseSequence;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@RequiredArgsConstructor
public class DefaultSequenceGeneratorService implements SequenceGeneratorService {
    private final MongoOperations mongoOperations;

    public long generateSequence(String sequenceFamily) {
        DatabaseSequence counter = getDatabaseSequence(sequenceFamily);
        return !Objects.isNull(counter) ?
                counter.getSeq()
                : 1;
    }

    private DatabaseSequence getDatabaseSequence(String sequenceFamily) {
        return mongoOperations.findAndModify(
                query(
                        where("_id").is(sequenceFamily)
                ),
                new Update().inc(
                        "seq",
                        1
                ),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class
        );
    }
}
