package ru.eltech.project.api.services;

import ru.eltech.project.api.data.Shoe;
import ru.eltech.project.api.data.Size;

import java.sql.SQLException;
import java.util.List;

public interface ShoeService {

    String addShoe(Shoe shoe);

    void delShoe(String id);

    Shoe getShoe(String id);

    List<Shoe> getShoesList() throws SQLException;

    void addShoeSize(Size size);

    void delShoeSize(Size size);

    List<Size> getSizes(String id);

    void updateShoe(Shoe shoe);
}
