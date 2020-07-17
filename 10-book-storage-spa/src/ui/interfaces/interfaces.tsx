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
