import axios from 'axios'
import { useEffect, useState } from 'react'

type Data = {
    data: [];
}

export const Hello = () => {

    const [data, setData] = useState<Data | string>('');

    useEffect(() => {
        axios.get('http://localhost:8080/api/pets')
        .then(response => {
            setData(response.data);
        })
        .catch((exception) => console.error(exception))
    }, []);


    return (
        <>
            <div>{data}</div>
        </>
    )
}