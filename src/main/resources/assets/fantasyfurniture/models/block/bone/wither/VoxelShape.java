Stream.of(
Block.box(0, 0, 0, 16, 3, 3),
Block.box(0, 8, 0, 16, 11, 3),
Block.box(0, 21, 0, 16, 24, 3),
Block.box(0, 29, 0, 16, 32, 3),
Block.box(14, 3, 0.5, 16, 29, 2.5),
Block.box(10.5, 3, 0.5, 12.5, 29, 2.5),
Block.box(7, 3, 0.5, 9, 29, 2.5),
Block.box(3.5, 3, 0.5, 5.5, 29, 2.5),
Block.box(0, 3, 0.5, 2, 29, 2.5)
).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();