import {useStyles} from "./Helpers.tsx";
import * as React from "react";
import {useState} from "react";
import {Button, ButtonGroup, TextField} from "@material-ui/core";
import {FormProps} from "../interfaces/interfaces";

export const GenreForm = (props: FormProps) => {
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [name, setName]: [string, Function] = useState();
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
                    name
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
                <TextField id="name" label="Name" value={name}
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