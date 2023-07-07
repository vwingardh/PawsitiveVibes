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
    const [petRemoved, setPetRemoved] = useState(0);

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
            const updatedFavorites = response.data.favorite;
            setAllPets(allPets.map(p => p.id === pet.id ? { ...p, favorite: updatedFavorites } : p))
        })
        .catch(error => {
            console.error(error);
        })
    }

    const handleDelete = (pet: PetDataProps) => {
        axios.delete('//localhost:8080/api/pets/' + pet.id)
        .then(response => {
          console.log(response);
          setAllPets(prevPets => prevPets.filter(p => p.id !== pet.id));
          setPetRemoved((petRemoved: number) => petRemoved + 1);
        })
        .catch((exception) => console.error(exception))
    }

    return (
        <>
            {allPets.map((pet) => (
                <div key={pet.id} className="card">
                    <ul className="card__list">
                        <li><img className="card__img" src={`http://localhost:8080/${pet.imgPath}`} /></li>
                        <li className="" onClick={() => handleClick(pet)}>Favorites: {pet.favorite}</li>
                        <CommentForm petId={pet.id} />
                        <button type="submit" onClick={() => handleDelete(pet)}>Delete</button>
                    </ul>
                </div>
            ))}
        </>
    )
}