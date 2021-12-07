package com.company.reccolldao.dao;

import com.company.reccolldao.model.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LabelDaoJdbcTemplateImpl implements LabelDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_LABEL_SQL =
            "insert into label (name, website) values (?, ?)";

    private static final String SELECT_LABEL_SQL =
            "select * from label where label_id = ?";

    private static final String SELECT_ALL_LABELS_SQL =
            "select * from label";

    private static final String UPDATE_LABEL_SQL =
            "update label set name = ?, website = ? where label_id = ?";

    private static final String DELETE_LABEL_SQL =
            "delete from label where label_id = ?";

    @Autowired
    public LabelDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Label addLabel(Label label) {

        jdbcTemplate.update(INSERT_LABEL_SQL, label.getName(), label.getWebsite());

        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        label.setId(id);

        return label;
    }

    @Override
    public Label getLabel(int id) {

        try {
            return jdbcTemplate.queryForObject(SELECT_LABEL_SQL, this::mapRowToLabel, id);
        } catch (EmptyResultDataAccessException e) {
            // if there are no labels with this given id, just return null
            return null;
        }
    }

    @Override
    public List<Label> getAllLabels() {

        return jdbcTemplate.query(SELECT_ALL_LABELS_SQL, this::mapRowToLabel);
    }

    @Override
    public void updateLabel(Label label) {

        jdbcTemplate.update(UPDATE_LABEL_SQL, label.getName(), label.getWebsite(), label.getId());

    }

    @Override
    public void deleteLabel(int id) {

        jdbcTemplate.update(DELETE_LABEL_SQL, id);

    }

    private Label mapRowToLabel(ResultSet rs, int rowNum) throws SQLException {
        Label label = new Label();
        label.setId(rs.getInt("label_id"));
        label.setName(rs.getString("name"));
        label.setWebsite(rs.getString("website"));

        return label;
    }
}
