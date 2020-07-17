import * as React from "react";
import '../styles/index.css';
import {Column, CustomTable} from "./CustomTable.tsx";
import {Author, Book, Genre} from "../interfaces/interfaces";

interface IProps {
}

interface IState {
}

interface Config {
    [any: string]: {
        dataUrl: string;
        columns: Column[]
    }
}

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
        ]
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
        ]
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
        ]
    }
}

class App extends React.Component<IProps, IState> {
    constructor(props: any) {
        super(props);
        this.state = {}
    }

    render() {
        return <>
            <CustomTable dataUrl={config.genres.dataUrl} columns={config.genres.columns}></CustomTable>
            <CustomTable dataUrl={config.authors.dataUrl} columns={config.authors.columns}></CustomTable>
            <CustomTable dataUrl={config.books.dataUrl} columns={config.books.columns}></CustomTable>
        </>
    }
}

export default App;