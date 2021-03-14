export interface Question
{
    id: number;
    qType: number;
    qText: number;
}

export interface Answer
{
    qId: number;
    answer: string;
}