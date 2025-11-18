package com.mtg.mtgservice.repository;

import com.mtg.mtgservice.model.ListItem;
import com.mtg.mtgservice.model.composite.ListItemID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListItemRepository extends JpaRepository<ListItem, ListItemID> {
  List<ListItem> findByIDListIDListID(Integer listID);
}
