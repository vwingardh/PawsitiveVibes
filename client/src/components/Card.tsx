import { useEffect, useState } from "react";
import axios from "axios";

type PetDataProps = {
    id: number;
    img: string;
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
        <div className="">
            <ul className="">
                {allPets.map((pet) => (
                    <>
                    <li key={pet.id}><img src={pet.img} /></li>
                    <li className="">{pet.favorite}</li>
                    </>
                ))}
            </ul>
        </div>
    )
}