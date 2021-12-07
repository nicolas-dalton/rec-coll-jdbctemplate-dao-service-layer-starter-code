package com.company.reccolldao.dao;

import com.company.reccolldao.model.Label;

import java.util.List;

public interface LabelDao {

    Label addLabel(Label label);

    Label getLabel(int id);

    List<Label> getAllLabels();

    void updateLabel(Label label);

    void deleteLabel(int id);

}
