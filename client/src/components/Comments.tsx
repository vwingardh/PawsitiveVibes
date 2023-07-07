type CommentDataProps = {
    id: number;
    message: string;
    created: string;
    favorite: number;
}

type CommentsProps = {
    allComments: CommentDataProps[];
}

export const Comments = ({ allComments, onDelete }: CommentsProps & { onDelete: (comment: CommentDataProps) => void}) => {
  return (
    <>
        {allComments.map((comment) => (
            <div key={comment.id} className="card">
                <ul className="card__list">
                    <li>{comment.message}</li>
                </ul>
                <button type="submit" onClick={() => onDelete(comment)}>Delete</button>
            </div>
        ))}
    </>
  )
}