import {Column} from "../components/CustomTable";
import * as React from "react";

export interface Genre {
    id: number;
    name: string;
}

export interface Author {
    id: number;
    firstName: string;
    lastName: string;
}

export interface Book {
    id: number;
    title: string;
    annotation: string;
    year: string;
    genres: Genre[];
    authors: Author[];
}

export interface IProps {
}

export interface Config {
    [any: string]: {
        dataUrl: string;
        columns: Column[]
        form: Function;
    }
}

export interface TabPanelProps {
    children?: React.ReactNode;
    index: any;
    value: any;
}

export interface FormProps {
    objectId: string;
    onClose: Function;
    request: Function;
    onDataChange: Function;
}