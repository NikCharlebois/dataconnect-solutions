/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT license. See LICENSE file in the project root for full license information.
 */

package com.microsoft.graphdataconnect.watercooler.common.db.entities.employee

import java.time.LocalDateTime

import com.fasterxml.jackson.annotation.{JsonIgnore, JsonProperty}
import javax.persistence.{Column, Embeddable, Id}

@Embeddable
class EmployeeIdentity( @Id
                        @JsonProperty("id")
                        @Column(name = "id", nullable = false)
                        var id: String,

                        @JsonIgnore
                        @Column(name = "version", columnDefinition = "TIMESTAMP")
                        var version: LocalDateTime) extends Serializable {


  def this() {
    this(null, null)
  }

  override def equals(that: Any): Boolean =
    that match {
      case ei: EmployeeIdentity => (this eq ei) && hashCode == ei.hashCode && id == ei.id && version == ei.version
      case _ => false
    }

  override def hashCode(): Int = ( 31 *  id.##) + ( 31 * version.## )

}

object EmployeeIdentity {
  def apply(id: String, version: LocalDateTime): EmployeeIdentity = new EmployeeIdentity(id, version)
}
