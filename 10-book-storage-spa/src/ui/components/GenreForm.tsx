import {useStyles} from "./Helpers.tsx";
import * as React from "react";
import {useEffect, useState} from "react";
import {Button, ButtonGroup, TextField} from "@material-ui/core";
import {FormProps, Genre} from "../interfaces/interfaces";

export const GenreForm = (props: FormProps) => {
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [name, setName]: [string, Function] = useState();
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
            if (!loaded && props.objectId) {
                props.request(`/genres/${props.objectId}`)
                    .then((r: any) => r.json())
                    .then((genre: Genre) => {
                        setLoaded(true);
                        setName(genre.name);
                    })
            }
        },
        [loaded]
    );
    const create = () => {
        setIsLoading(true);
        props.request(
            '/genres',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    name: name.trim()
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
            `/genres/${props.objectId}`,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: props.objectId,
                    name: name.trim()
                })
            }
        )
            .then(() => {
                props.onClose();
                props.onDataChange();
            })
            .catch(() => setIsLoading(false))
    };

    return <form className={classes.root} noValidate autoComplete="off">
        <div className={"form-input"}>
            <div>
                <TextField id="name" label="Name" value={name}
                           defaultValue={" "}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setName(event.target.value)}
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
                    size="small"
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
                            size="small"
                        >
                            Update
                        </Button> :
                        <Button
                            variant="contained"
                            color="primary"
                            onClick={() => create()}
                            disabled={isLoading}
                            size="small"
                        >
                            Create
                        </Button>
                }
            </ButtonGroup>
        </div>
    </form>
}