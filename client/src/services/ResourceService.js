const characters = [
  {
    id: 1,
    name: 'Name: Lena Brightspear',
    appearance: 'Appearance: An enormous scar bisects their face, and they avoid eye contact.',
    definingTrait: 'Defining Trait: Their loyal animal companion always at their side.',
    role: 'Role: Thief',
  },

  {
    id: 2,
    name: 'Name: Gregori Valkas',
    appearance: 'Appearance: A hulking stature, but a kind smile.',
    definingTrait: 'Defining Trait: Their habit of humming and whistling at all times.',
    role: 'Role: Warrior',
  },

  {
    id: 3,
    name: 'Name: Heather Thornspur',
    appearance: 'Appearance: Short and stout, like a barrel.',
    definingTrait: "Defining Trait: Happy-go-lucky. Can't resist a good gamble.",
    role: 'Role: Cleric',
  },

  {
    id: 4,
    name: 'Name: Yrsa Ufgood',
    appearance: 'Appearance: An enormous scar bisects their face. They avoid eye contact.',
    definingTrait: 'Defining Trait: The holy symbol always around their neck.',
    role: 'Role: Wizard',
  },
  
  {
    id: 5,
    name: 'Name: Namic Kingsley',
    appearance: 'Appearance: Pale gold hair and watery blue eyes.',
    definingTrait: 'Defining Trait: Long and thin, like a spider.',
    role: 'Role: Necromancer',
  },
];

const resourceService = {
  getCharacters() {
    return characters;
  }
};

export { resourceService };
