import * as React from "react";
import {useState} from "react";
import '../styles/index.css';
import {CustomTable} from "./CustomTable.tsx";
import {Author, Book, Config, Genre} from "../interfaces/interfaces";
import {AppBar, CircularProgress, Drawer, Fab, Tab, Tabs} from "@material-ui/core";
import Alert from '@material-ui/lab/Alert';
import {a11yProps, getRequest, TabPanel, useStyles} from "./Helpers.tsx";
import {BookForm} from "./BookForm.tsx";
import {AuthorForm} from "./AuthorForm.tsx";
import {GenreForm} from "./GenreForm.tsx";

const config: Config = {
    books: {
        dataUrl: '/books.json',
        columns: [
            {
                title: 'ID',
                accessor: (e: Book) => e.id
            },
            {
                title: 'Title',
                accessor: (e: Book) => e.title
            },
            {
                title: 'Annotation',
                accessor: (e: Book) => e.annotation
            },
            {
                title: 'Year',
                accessor: (e: Book) => e.year
            },
            {
                title: 'Genres',
                accessor: (e: Book) => e.genres.map((g: Genre) => g.name).join(', ')
            },
            {
                title: 'Authors',
                accessor: (e: Book) => e.authors.map((a: Author) => `${a.firstName} ${a.lastName}`).join(', ')
            }
        ],
        form: BookForm
    },
    authors: {
        dataUrl: '/authors.json',
        columns: [
            {
                title: 'ID',
                accessor: (e: Author) => e.id
            },
            {
                title: 'First Name',
                accessor: (e: Author) => e.firstName
            },
            {
                title: 'Last Name',
                accessor: (e: Author) => e.lastName
            }
        ],
        form: AuthorForm
    },
    genres: {
        dataUrl: '/genres.json',
        columns: [
            {
                title: 'ID',
                accessor: (e: Genre) => e.id
            },
            {
                title: 'Name',
                accessor: (e: Genre) => e.name
            }
        ],
        form: GenreForm
    }
}
const tabs: { [any: number]: string } = {
    0: 'books',
    1: 'authors',
    2: 'genres'
}

export default function App() {
    const classes = useStyles();
    const [tab, setTab] = React.useState('books');
    const [objectId, setObjectId]: [string, Function] = React.useState();
    const [formShown, setFormShown] = React.useState(false);
    const [isLoading, setIsLoading] = React.useState(false);
    const [errorMessage, setErrorMessage]: [string, Function] = useState();
    const [updateIndicator, setUpdateIndicator] = useState(Math.random);

    const request = getRequest(setIsLoading, setErrorMessage)
    const changeTab = (event: React.ChangeEvent<{}>, newValue: number) => {
        setTab(tabs[newValue]);
    };
    const openObject = (objectId: string,) => {
        setObjectId(objectId);
        setFormShown(true);
    }
    const openCreateForm = () => {
        setObjectId(null);
        setFormShown(true)
    }

    const Form = config[tab].form;
    return (
        <div className={`${classes.root} main`}>
            <AppBar position="fixed">
                <Tabs value={tab} onChange={changeTab} aria-label="simple tabs example">
                    <Tab label="Books" {...a11yProps('books')} />
                    <Tab label="Authors" {...a11yProps('authors')} />
                    <Tab label="Genres" {...a11yProps('genres')} />
                </Tabs>
            </AppBar>
            <div className="content">
                <TabPanel value={tab} index={'books'}>
                    <CustomTable dataUrl={config.books.dataUrl} columns={config.books.columns}
                                 request={request} key={`${updateIndicator}-books`}
                                 onOpen={openObject}/>
                </TabPanel>
                <TabPanel value={tab} index={'authors'}>
                    <CustomTable dataUrl={config.authors.dataUrl} columns={config.authors.columns}
                                 request={request} key={`${updateIndicator}-authors`}
                                 onOpen={openObject}/>
                </TabPanel>
                <TabPanel value={tab} index={'genres'}>
                    <CustomTable dataUrl={config.genres.dataUrl} columns={config.genres.columns}
                                 request={request} key={`${updateIndicator}-genres`}
                                 onOpen={openObject}/>
                </TabPanel>
                <Fab size="small" color="secondary" aria-label="add" className={"add-button"}
                     onClick={openCreateForm}
                >
                    Add
                </Fab>
            </div>
            <Drawer anchor={'right'} open={formShown} onClose={() => setObjectId(null)}>
                <Form
                    objectId={objectId}
                    onClose={() => setFormShown(false)}
                    request={request}
                    onDataChange={() => setUpdateIndicator(Math.random())}
                />
            </Drawer>
            {
                isLoading && <CircularProgress className={"spinner"}/>
            }
            {
                errorMessage && <Alert severity="error" className={"notification"}>
                    {errorMessage}
                </Alert>
            }
        </div>
    );
}