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
            setAllPets(response.data.map(pet => ({ ...pet, favorite: 0 })))
        })
        .catch(error => {
            console.error(error);
        })
    }, [newPet]);

    const handleClick = (pet: PetDataProps) => {
        axios.post('//localhost:8080/api/pets/' + pet.id + '/favorites')
        .then(response => {
            setAllPets(allPets.map(p => p.id === pet.id ? { ...p, favorite: response.data.favorites } : p))
        })
        .catch(error => {
            console.error(error);
        })
    }

    return (
        <>
            {allPets.map((pet) => (
                <div key={pet.id} className="card">
                    <ul className="card__list">
                        <li><img className="card__img" src={`http://localhost:8080/${pet.imgPath}`} /></li>
                        <li className="" onClick={() => handleClick(pet)}>Favorites: {pet.favorite}</li>
                        <CommentForm petId={pet.id} />
                    </ul>
                </div>
            ))}
        </>
    )
}