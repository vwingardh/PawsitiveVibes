type CommentDataProps = {
    id: number;
    message: string;
    created: string;
    favorite: number;
}

type CommentsProps = {
    allComments: CommentDataProps[];
}

export const Comments = ({ allComments }: CommentsProps) => {
  return (
    <>
        {allComments.map((comment) => (
            <div key={comment.id} className="card">
                <ul className="card__list">
                    <li>{comment.message}</li>
                </ul>
            </div>
        ))}
    </>
  )
}