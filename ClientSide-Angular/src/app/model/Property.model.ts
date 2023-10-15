import { Adress } from "./Adress.model";
import { Announcement } from "./Announcement.model";

export class Property
{
    id_property ?: number;
    description ?: string;
    x ?: number;
    closed ?: boolean
    y ?: number;
    type ?: string;
    adresse ?: Adress;
    announcement ?: Announcement ;
}