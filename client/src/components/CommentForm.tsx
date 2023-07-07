import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Comments } from './Comments'

type PetIdProps = {
    petId: number;
}

type CommentDataProps = {
    comment: string;
}

type CommentsDataProps = {
    id: number;
    message: string;
    created: string;
    favorite: number;
}

export const CommentForm = ({ petId }: PetIdProps) => {

    const [comment, setComment] = useState('');
    const [allComments, setAllComments] = useState([]);
    const [commentCount, setCommentCount] = useState(0);

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        postComment({comment});
        setComment('');
    }

    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setComment(event.target.value);
    }

    const postComment = ({comment}: CommentDataProps) => {
        axios.post('//localhost:8080/api/pets/' + petId + '/comments', {
            message: comment
        })
        .then(response => {
            console.log(response);
            setCommentCount((commentCount: number) => commentCount + 1);
        })
        .catch((exception) => console.error(exception))
    }

    const handleDelete = (comment: CommentsDataProps) => {
        axios.delete('//localhost:8080/api/pets/' + comment.id + '/comments')
        .then(response => {
          console.log(response);
          setAllComments(allComments.filter(c => c.id !== comment.id));
        })
        .catch((exception) => console.error(exception))
    }

    useEffect(() => {
        axios.get('//localhost:8080/api/pets/' + petId + '/comments')
        .then(response => {
            setAllComments(response.data)
        })
        .catch(error => {
            console.log(error);
        })
    }, [commentCount, petId]);

    return (
        <>
        <form className="" method="post" onSubmit={handleSubmit}>
            <label className="form__label" htmlFor="newCommentForm">Leave a comment: </label>
            <input type="text" className="form__input-name" value={comment} onChange={handleChange} placeholder="Enter Message" required />
            <button type="submit" className="form__button-addDev">Submit</button>
        </form>
        <Comments allComments={allComments} onDelete={handleDelete}/>
        </>
    )
}
