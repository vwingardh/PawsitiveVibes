import { useEffect, useState } from "react";
import axios from "axios";
import { CommentForm } from './CommentForm'

type PetDataProps = {
    id: number;
    imgPath: string;
    tag: string;
    created: string;
    favorite: number;
}

type NewPetProp = {
    newPet: number;
}

export const Card = ({ newPet }: NewPetProp ) => {
    const [allPets, setAllPets] = useState<Array<PetDataProps>>([]);

    useEffect(() => {
        axios.get('//localhost:8080/api/pets')
        .then(response => {
            setAllPets(response.data)
        })
        .catch(error => {
            console.error(error);
        })
    }, [newPet]);

    return (
        <>
            {allPets.map((pet) => (
                <div key={pet.id} className="card">
                    <ul className="card__list">
                        <li><img className="card__img" src={`http://localhost:8080/${pet.imgPath}`} /></li>
                        <li className="">Favorites: {pet.favorite}</li>
                        <CommentForm petId={pet.id} />
                    </ul>
                </div>
            ))}
        </>
    )
}