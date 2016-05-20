package org.jetbrains.squash.dialects.h2

import org.jetbrains.squash.dialect.*
import org.jetbrains.squash.drivers.*
import java.sql.*

class H2DatabaseSchema(dialect: SQLDialect, transaction: JDBCTransaction) : JDBCDatabaseSchema(dialect, transaction) {
    override fun currentSchema(): String {
        val preparedStatement = transaction.jdbcConnection.prepareStatement("SELECT * FROM INFORMATION_SCHEMA.SESSION_STATE WHERE KEY='SCHEMA_SEARCH_PATH'")
        val resultSet = preparedStatement.executeQuery()
        if (resultSet.next()) {
            return resultSet.getString(1)
        }
        return ""
    }
}