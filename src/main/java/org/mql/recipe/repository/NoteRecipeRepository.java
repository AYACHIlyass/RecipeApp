package org.mql.recipe.repository;

import org.mql.recipe.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRecipeRepository extends JpaRepository<Note,Long> {
}
