import {useStyles} from "./Helpers.tsx";
import * as React from "react";
import {useEffect, useState} from "react";
import {Button, ButtonGroup, TextField} from "@material-ui/core";
import {Author, Book, FormProps, Genre} from "../interfaces/interfaces";

export const BookForm = (props: FormProps) => {
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [title, setTitle]: [string, Function] = useState();
    const [annotation, setAnnotation]: [string, Function] = useState();
    const [year, setYear]: [string, Function] = useState();
    const [genres, setGenres]: [string, Function] = useState();
    const [authors, setAuthors]: [string, Function] = useState();
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
            if (!loaded && props.objectId) {
                props.request(`/books/${props.objectId}.json`)
                    .then((r: any) => r.json())
                    .then((book: Book) => {
                        setLoaded(true);
                        setTitle(book.title);
                        setAnnotation(book.annotation);
                        setYear(book.year);
                        setAuthors(book.authors.map((a: Author) => a.id).join(','));
                        setGenres(book.genres.map((g: Genre) => g.id).join(','));
                    })
            }
        },
        [loaded]
    );
    const create = () => {
        setIsLoading(true);
        props.request(
            '/books',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    title: title.trim(),
                    annotation: annotation.trim(),
                    year: year.trim(),
                    genres: genres.trim(),
                    authors: authors.trim()
                })
            }
        )
            .then(() => {
                props.onClose();
                props.onDataChange();
            })
            .catch(() => setIsLoading(false))
    }
    const update = () => {
        setIsLoading(true);
        props.request(
            `/books/${props.objectId}`,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: props.objectId,
                    title: title.trim(),
                    annotation: annotation.trim(),
                    year: year.trim(),
                    genres: genres.trim(),
                    authors: authors.trim()
                })
            }
        )
            .then(() => {
                props.onClose();
                props.onDataChange();
            })
            .catch(() => setIsLoading(false))
    }

    return <form className={classes.root} noValidate autoComplete="off">
        <div className={"form-input"}>
            <div>
                <TextField id="title" label="Title" value={title}
                           defaultValue={" "}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setTitle(event.target.value)}
                />
            </div>
            <div>
                <TextField id="annotation" label="Annotation" value={annotation}
                           defaultValue={" "}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setAnnotation(event.target.value)}
                />
            </div>
            <div>
                <TextField id="year" label="Year" value={year}
                           defaultValue={" "}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setYear(event.target.value)}
                />
            </div>
            <div>
                <TextField id="genres" label="Genres" value={genres}
                           defaultValue={" "}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setGenres(event.target.value)}
                />
            </div>
            <div>
                <TextField id="authors" label="Authors" value={authors}
                           defaultValue={" "}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setAuthors(event.target.value)}
                />
            </div>
        </div>
        <div className={"form-input"}>
            <ButtonGroup aria-label="outlined primary button group">
                <Button
                    variant="contained"
                    color="secondary"
                    onClick={() => props.onClose()}
                    disabled={isLoading}
                >
                    Close
                </Button>
                {
                    props.objectId ?
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={() => update()}
                            disabled={isLoading}
                        >
                            Update
                        </Button> :
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={() => create()}
                            disabled={isLoading}
                        >
                            Create
                        </Button>
                }
            </ButtonGroup>
        </div>
    </form>
}