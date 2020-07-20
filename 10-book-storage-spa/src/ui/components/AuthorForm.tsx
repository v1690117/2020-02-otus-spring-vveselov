import {useStyles} from "./Helpers.tsx";
import * as React from "react";
import {useEffect, useState} from "react";
import {Button, ButtonGroup, TextField} from "@material-ui/core";
import {Author, FormProps} from "../interfaces/interfaces";

export const AuthorForm = (props: FormProps) => {
    const classes = useStyles();
    const [isLoading, setIsLoading] = useState(false);
    const [firstName, setFirstName]: [string, Function] = useState();
    const [lastName, setLastName]: [string, Function] = useState();
    const [loaded, setLoaded] = useState(false);

    useEffect(() => {
            if (!loaded && props.objectId) {
                props.request(`/authors/${props.objectId}.json`)
                    .then((r: any) => r.json())
                    .then((author: Author) => {
                        setLoaded(true);
                        setFirstName(author.firstName);
                        setLastName(author.lastName);
                    })
            }
        },
        [loaded]
    );

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
                    firstName: firstName.trim(),
                    lastName: lastName.trim()
                })
            }
        )
            .then(() => {
                props.onClose()
                props.onDataChange();
            })
            .catch(() => setIsLoading(false))
    }
    const update = () => {
        setIsLoading(true);
        props.request(
            `/authors/${props.objectId}`,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    id: props.objectId,
                    firstName: firstName.trim(),
                    lastName: lastName.trim()
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
                <TextField id="firstName" label="First Name" value={firstName}
                           defaultValue={" "}
                           onChange={(event: React.ChangeEvent<HTMLInputElement>) => setFirstName(event.target.value)}
                />
            </div>
            <div>
                <TextField id="lastName" label="Last Name" value={lastName}
                           defaultValue={" "}
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