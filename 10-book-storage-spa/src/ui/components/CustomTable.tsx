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

export interface Column {
    title: string;
    accessor: Function
}

interface TableProps extends IProps {
    dataUrl: string;
    columns: Column[];
    request: Function;
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
                            <TableCell>{column.title}</TableCell>
                        ))}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {this.state.data.map((element: any) => (
                        <TableRow key={element.id}>
                            {
                                this.props.columns.map((column: Column) => (
                                    <TableCell component="th" scope="row">
                                        {column.accessor(element)}
                                    </TableCell>
                                ))
                            }
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    }
}