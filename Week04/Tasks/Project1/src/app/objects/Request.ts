export class Request{
    id: number;
    fullAmmount: number;
    cooperateAmmount: number;
    status: number;
    approvalDate: string;
    creationDate: string;
    eventDate: string;
    gradingFormat: string;
    eventDescription: string;
    eventJustification: string;
    eventLocation: string;
    typeValue: number;
    typeName: string;
    typeId: number;
    files: { file }[];
    info: { info }[];
}