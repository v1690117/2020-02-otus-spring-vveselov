import {Box, Theme, Typography} from "@material-ui/core";
import {makeStyles} from "@material-ui/core/styles";
import * as React from "react";
import {TabPanelProps} from "../interfaces/interfaces";

export const useStyles = makeStyles((theme: Theme) => ({
    root: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.paper,
    }
}));

export function TabPanel(props: TabPanelProps) {
    const {children, value, index, ...other} = props;
    return (
        <div
            role="tabpanel"
            hidden={value !== index}
            id={`simple-tabpanel-${index}`}
            aria-labelledby={`simple-tab-${index}`}
            {...other}
        >
            {value === index && (
                <Box p={3} style={{padding: '0'}}>
                    <Typography>{children}</Typography>
                </Box>
            )}
        </div>
    );
}

export function a11yProps(index: any) {
    return {
        id: `simple-tab-${index}`,
        'aria-controls': `simple-tabpanel-${index}`,
    };
}

export function getRequest(setIsLoading: (value: (((prevState: boolean) => boolean) | boolean)) => void, setErrorMessage: Function) {
    return (input: RequestInfo, init?: RequestInit): Promise<Response> => Promise.resolve(setIsLoading(true))
        .then(() => fetch(input, init))
        .then(r => {
            if (r.status >= 400) {
                throw new Error(r.statusText);
            }
            setIsLoading(false);
            return r;
        })
        .catch(err => {
            setIsLoading(false);
            setErrorMessage(err.message);
            setTimeout(() => setErrorMessage(null), 3000); // todo change thru notification queue
            return err;
        });
}