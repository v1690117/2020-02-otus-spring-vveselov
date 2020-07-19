import {useStyles} from "./Helpers.tsx";
import * as React from "react";
import {useState} from "react";
import {Button, ButtonGroup, TextField} from "@material-ui/core";
import {FormProps} from "../interfaces/interfaces";

export const AuthorForm = (props: FormProps) => {
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [firstName, setFirstName]: [string, Function] = useState();
    const [lastName, setLastName]: [string, Function] = useState();

    const create = () => {
        setIsLoading(true);
        props.request(
            '/authors',
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    firstName,
                    lastName
                })
            }
        )
            .then(() => {
                props.onClose()
                props.onDataChange();
            })
            .catch(() => setIsLoading(false))
    }

    return <form className={classes.root} noValidate autoComplete="off">
        <div className={"form-input"}>
            <div>
                <TextField id="firstName" label="First Name" value={firstName}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setFirstName(event.target.value)}
                />
            </div>
            <div>
                <TextField id="lastName" label="Last Name" value={lastName}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setLastName(event.target.value)}
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