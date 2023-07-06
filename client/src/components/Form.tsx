import { useState } from 'react'
import axios from 'axios'
import { Card } from './Card'

type FormDataProps = {
    image: File | null ;
    tag: string;
}

const pets = [
    { value: 'cat', label: 'Cat'},
    { value: 'dog', label: 'Dog'}
] 

export const Form = () => {
    const [tag, setTag] = useState(pets[0].value);
    const [selectedFile, setSelectedFile] = useState<File | null>(null);
    const [newPet, setNewPet] = useState<number>(0);

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        postPet({image: selectedFile, tag: tag})
    }

    const handleImage = (event: React.ChangeEvent<HTMLInputElement>) => {
        if (event.target.files) {
            setSelectedFile(event.target.files[0]);
        }
    }

    const handleTag = (event: React.ChangeEvent<HTMLSelectElement>) => {
        event.preventDefault();
        setTag(event.target.value);
    }

    const postPet = ({image, tag}: FormDataProps) => {
        const formData = new FormData();
        if (image) {
            formData.append('image', image);
        }
        formData.append('tag', tag);

        axios.post('//localhost:8080/api/pets', formData, {
            headers: {'Content-Type': 'multipart/form-data'}
        })
        .then(response => {
            console.log(response);
            setNewPet((newPet: number) => newPet + 1);
        })
        .catch((exception) => console.error(exception))
    }

    return (
        <>
        <form className="" method="post" onSubmit={handleSubmit}>
            <h2 className="">Upload a Pet</h2>
            <label className="" htmlFor="chooseFile">Upload: </label>
            <input name="chooseFile" onChange={handleImage} type="file" className="" accept="image/png, image/jpeg" required />
        
            <label className="" htmlFor="petTag">Choose Pet: </label>
                <select id="newDeveloperBootcamp" className="" onChange={handleTag}>
                    {pets.map((option) => (
                        <option key={option.value} value={option.value}>{option.label}</option>
                    ))}
                </select>
                <button type="submit" className="">Upload</button>
        </form>
        <Card newPet={newPet}/>
        </>
    )
}