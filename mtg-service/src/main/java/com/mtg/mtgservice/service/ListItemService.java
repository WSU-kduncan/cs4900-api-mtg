package com.mtg.mtgservice.service;

import com.mtg.mtgservice.model.ListItem;
import com.mtg.mtgservice.model.composite.ListItemID;
import com.mtg.mtgservice.model.Lists;
import com.mtg.mtgservice.model.Card;
import com.mtg.mtgservice.repository.ListItemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;        
import java.util.Optional; 

@Service
@RequiredArgsConstructor
public class ListItemService {

    private final ListItemRepository repo;

    @PersistenceContext
    private EntityManager em;

    public java.util.List<ListItem> getAll() {
        return repo.findAll();
    }

    public Optional<ListItem> getById(Integer listId, Integer cardNumber, String setName) {
        var id = new ListItemID();
        id.setListID(em.getReference(Lists.class, listId));
        var cardPk = new com.mtg.mtgservice.model.composite.CardID(cardNumber, setName);
        id.setCard(em.getReference(Card.class, cardPk));
        return repo.findById(id);
    }

    public ListItem save(ListItem entity){
        return repo.save(entity);
    }

    public List<ListItem> getByList(Integer listId){
        return repo.findByIDListIDListID(listId);
    }

    public ListItem saveFromDto(Integer listId, Integer cardNumber, String setName, Integer quantityWanted) {
        var id = new ListItemID();
        id.setListID(em.getReference(Lists.class, listId));

        var cardPk = new com.mtg.mtgservice.model.composite.CardID(cardNumber, setName);
        id.setCard(em.getReference(Card.class, cardPk));

        var entity = new ListItem();
        entity.setID(id);
        entity.setQuantityWanted(quantityWanted);

        return repo.save(entity);
    }
    
}
