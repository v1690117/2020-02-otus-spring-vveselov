import * as React from "react";
import 'antd/dist/antd.css';
import {Table} from "antd";

interface Genre {
    id: number;
    name: string;
}

interface Author {
    id: number;
    firstName: string;
    lastName: string;
}

const columns = [
    {
        title: 'Title',
        dataIndex: 'title',
        key: 'title',
    },
    {
        title: 'Annotation',
        dataIndex: 'annotation',
        key: 'annotation',
    },
    {
        title: 'Year',
        dataIndex: 'year',
        key: 'year',
    },
    {
        title: 'Genres',
        render: (text: any, record: any, index: any) => record.genres.map((g: Genre) => g.name).join(', '),
        key: 'year',
    },
    {
        title: 'Authors',
        render: (text: any, record: any, index: any) => record.authors.map((a: Author) => `${a.firstName} ${a.lastName}`).join(', '),
        key: 'year',
    },
];

class App extends React.Component<any, any> {
    constructor(props: any) {
        super(props);
        this.state = {
            books: []
        }
    }

    componentDidMount() {
        fetch('/books.json')
            .then(r => r.json())
            .then(res => this.setState({
                books: res
            }))
    }

    getData() {
        return this.state.books;
    }

    render() {
        return <Table dataSource={this.getData()} columns={columns}/>
    }
}

export default App;