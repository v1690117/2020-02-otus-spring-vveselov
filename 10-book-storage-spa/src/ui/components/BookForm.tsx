import {useStyles} from "./Helpers.tsx";
import * as React from "react";
import {useState} from "react";
import {Button, ButtonGroup, TextField} from "@material-ui/core";
import {FormProps} from "../interfaces/interfaces";

export const BookForm = (props: FormProps) => {
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [title, setTitle]: [string, Function] = useState();
    const [annotation, setAnnotation]: [string, Function] = useState();
    const [year, setYear]: [string, Function] = useState();
    const [genres, setGenres]: [string, Function] = useState();
    const [authors, setAuthors]: [string, Function] = useState();

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
                    title,
                    annotation,
                    year,
                    genres,
                    authors
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
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setTitle(event.target.value)}
                />
            </div>
            <div>
                <TextField id="annotation" label="Annotation" value={annotation}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setAnnotation(event.target.value)}
                />
            </div>
            <div>
                <TextField id="year" label="Year" value={year}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setYear(event.target.value)}
                />
            </div>
            <div>
                <TextField id="genres" label="Genres" value={genres}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setGenres(event.target.value)}
                />
            </div>
            <div>
                <TextField id="authors" label="Authors" value={authors}
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
                <Button
                    variant="contained"
                    color="primary"
                    onClick={() => create()}
                    disabled={isLoading}
                >
                    Create
                </Button>
            </ButtonGroup>
        </div>
    </form>
}