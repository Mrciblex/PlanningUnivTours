--[[

- SLOT DE 15 MIN
- PLACER LES COURS POUR LES PROFS QUI ONT LE MOINS DE DISPONIBILITES EN PRIORITE (avant les autres)

]]

local semestre = {
  debut = os.time{year=2025, month=9, day=1},
  fin = os.time{year=2025, month=9, day=14}
}

-- Jours fériés et vacances automatiquement détécté par le programme + personnalisé si besoin
local momentsBanalises = {
  -- exemple de jour férié
  {
    nom = "La Musa Party",
    debut = os.time{year=2025, month=9, day=2, hour=0, min=0, sec=0},
    fin = os.time{year=2025, month=9, day=2, hour=23, min=59, sec=59}
  },
  {
    nom = "Les vacances de Musa",
    debut = os.time{year=2025, month=9, day=10, hour=0, min=0, sec=0},
    fin = os.time{year=2025, month=9, day=12, hour=23, min=59, sec=59}
  }
}

-- STATIC
local limitesPlanning = {
  debut = 8 * 60, -- 8h00 en minutes
  fin = 20 * 60 -- 20h00 en minutes
}

-- Dans une journée classique
local slotsBloques = {
    {debut = 10*60, fin = 10*60 + 15},   -- 10h00-10h15
    {debut = 12*60 + 15, fin = 13*60 + 30}, -- 12h15-13h30
    {debut = 15*60 + 30, fin = 15*60 + 45}  -- 15h30-15h45
}

local dureeSlot = 15
local slotsDisponibles = {}

function estBloque(debut, fin, slotsBloques)
    for _, slot in ipairs(slotsBloques) do
        if debut < slot.fin and fin > slot.debut then
            return true
        end
    end
    return false
end

-- Création des slots disponibles par jours par défaut (Prend pas en compte les profs ici mais les limites du planning)
for t = limitesPlanning.debut, limitesPlanning.fin - dureeSlot, dureeSlot do
    local slotDebut = t
    local slotFin = t + dureeSlot

    if not estBloque(slotDebut, slotFin, slotsBloques) then
        table.insert(slotsDisponibles, {
          debut = slotDebut, 
          fin = slotFin,
          usedBy = {} -- COURS
        })
    end
end

--[[ Verif des slots
for _, s in ipairs(slotsDisponibles) do
    print(string.format("%02d:%02d - %02d:%02d",
        math.floor(s.debut/60), s.debut%60,
        math.floor(s.fin/60), s.fin%60))
end
]]

local profs = {
  {
    nom = "Brouard",
    prenom = "Thierry",
    intervenantExterieur = false,
    jours = {
      [1] = {
        disponibilites = {
          {
            debut = 8 * 60, -- 08:00,
            fin = 13 * 60 + 30 -- 13:30
          },
          {
            debut = 15 * 60 + 30, -- 15:30,
            fin = 18 * 60 -- 18:00
          }
        }
      },
      [2] = {
        disponibilites = {
        }
      },
      [3] = {
        disponibilites = {
        }
      },
      [4] = {
        disponibilites = {
        }
      },
      [5] = {
        disponibilites = {
        }
      },
      [6] = {
        disponibilites = {
        }
      },
      [7] = {
        disponibilites = {
        }
      }
    }
  },
  {
    nom = "Brouard",
    prenom = "Helene",
    intervenantExterieur = false,
    jours = {
      [1] = {
        disponibilites = {
          {
            debut = 15 * 60 + 30, -- 15:30,
            fin = 18 * 60 -- 18:00
          }
        }
      },
      [2] = {
        disponibilites = {
        }
      },
      [3] = {
        disponibilites = {
        }
      },
      [4] = {
        disponibilites = {
        }
      },
      [5] = {
        disponibilites = {
        }
      },
      [6] = {
        disponibilites = {
        }
      },
      [7] = {
        disponibilites = {
        }
      }
    }
  }
}

local composantes = {
  nom = "test",
  volumeHTotal = 4 * 60,
  volumeHCM = 2 * 60,
  volumeTP = 2 * 60,
  volumeTD = 0,
  blocHCM = 2 * 60,
  blocHTP = 2 * 60,
  blocHTD = 2 * 60
}

-- (-10 à +10)
local priorites = {
  trous = 8, -- Evite les trous dans l'emploi du temps
  eviteFinTard = 5, -- Evite les cours tard le soir
  matin = 3, -- Cours le matin
  commenceDirect = 5, -- Cours à 8h
  memeCoursMemeJour = 5 -- Même composantes dans la même journée avec même groupe
}

-- Calcule la disponibilite du prof pour prioriser les cours des profs avec peu de disponibilite
function dispoTotale(prof)
    local total = 0
    for jour, data in pairs(prof.jours) do
        for _, plage in ipairs(data.disponibilites) do
            total = total + (plage.fin - plage.debut)
        end
    end
    return total
end

table.sort(profs, function(a, b)
        return dispoTotale(a) < dispoTotale(b)  -- moins dispo = priorité
    end)

function diffDaysSafe(t1, t2)
    return math.floor((t2 - t1) / 86400)
end

local diffDays = diffDaysSafe(semestre.debut, semestre.fin)

-- Ratio CM, TP, TD
function ratioParSemaine(c) -- c : composantes
  local jours = diffDays
  local semaines = math.ceil(jours / 7)
  
  if semaines <= 0 then
    return 0, 0, 0
  end
  
  local seancesCM = c.volumeHCM / c.blocHCM
  local seancesTP = c.volumeTP / c.blocHTP
  local seancesTD = c.volumeTD / c.blocHTD

  
  return 
    seancesCM / semaines,
    seancesTP / semaines,
    seancesTD / semaines
end

local cm, tp, td = ratioParSemaine(composantes)
local ratioSemaine = { CM = cm, TP = tp, TD = td }

-- Pour le placement par semaine de façon équilibré avec les ratio
local accum = { CM = 0, TP = 0, TD = 0 }

-- print(ratioSemaine.CM, ratioSemaine.TP, ratioSemaine.TD);

local prof_composante = {
  "Thierry" = {
    nom = "test",
    typeCours = "CM"
  },
  "Thierry" = {
    nom = "test",
    typeCours = "TP"
  },
  "Helene" = {
    nom = "test",
    typeCours = "TD"
  },
}

-- BOUCLE GENERAL
for i = 0, diffDays do
    local actualDate = semestre.debut + i * 24 * 60 * 60
    print(" ")
    print(os.date("%Y-%m-%d", actualDate) .. " :")
    
    
    -- BOUCLE WHILE PAR DESSUS AVEC TANT QUE N < NBPROFS POUR QUE ON RESSAIE DANS UN AUTRE ORDRE DES PROFS
    -- Problème : toutes les combinaisons seront testé pour le meilleure score donc parfait par semaine mais compléxité O(2^n) horrible
    -- Solution : Memoïsation + abandon dès que score < bestActualPlanning + abandon des branches impossible (comment detecter avant la fin du calcul du planning ?)
    -- Eviter O(2^n) : Taux d'acceptation de score (si 85/100 alors on cherche pas plus et on prend ce planning)
    -- Eviter O(2^n) : ...
        
    -- Placement des cours associés à ce prof
    for i, prof in ipairs(profs) do
      print(prof.prenom)
    end
    
    -- print("Cours de ... à xxhxx")
end
