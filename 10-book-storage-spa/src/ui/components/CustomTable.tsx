import * as React from "react";
import '../styles/index.css';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {IProps} from "../interfaces/interfaces";
import {Button} from "@material-ui/core";

export interface Column {
    title: string;
    accessor: Function
}

interface TableProps extends IProps {
    dataUrl: string;
    columns: Column[];
    request: Function;
    onOpen: Function;
    onDelete: Function;
}

interface TableState extends IProps {
    data: {
        id: string;
        [any: string]: any;
    }[];
}

export class CustomTable extends React.Component<TableProps, TableState> {
    constructor(props: any) {
        super(props);
        this.state = {
            data: []
        }
    }

    componentDidMount() {
        this.props.request(this.props.dataUrl)
            .then((r: any) => r.json())
            .then((res: any) => this.setState({
                data: res
            }))
    }

    render() {
        return <TableContainer component={Paper}>
            <Table aria-label="simple table">
                <TableHead>
                    <TableRow>
                        {this.props.columns.map((column: Column) => (
                            <TableCell key={Math.random()}>{column.title}</TableCell>
                        ))}
                        <TableCell></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {this.state.data.map((element: any) => (
                        <TableRow key={element.id}>
                            {
                                this.props.columns.map((column: Column) => (
                                    <TableCell component="th" scope="row" key={`${element.id}${column.title}`}>
                                        {column.accessor(element)}
                                    </TableCell>
                                ))
                            }
                            <TableCell component="th" scope="row">
                                <Button
                                    variant="contained"
                                    color="primary"
                                    onClick={() => this.props.onOpen(element.id)}
                                    size="small"
                                >
                                    Edit
                                </Button>
                                <Button
                                    variant="contained"
                                    color="secondary"
                                    onClick={() => this.props.onDelete(element.id)}
                                    size="small"
                                >
                                    Delete
                                </Button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    }
}